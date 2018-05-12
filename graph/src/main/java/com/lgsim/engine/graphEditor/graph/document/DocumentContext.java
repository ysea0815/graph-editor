package com.lgsim.engine.graphEditor.graph.document;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.graph.IDocument;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.JarUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiPredicate;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

@SuppressWarnings("WeakerAccess")
public class DocumentContext {

  private static final Logger log = LoggerFactory.getLogger(DocumentContext.class);
  private Editor editor;
  private IApplication application;


  public DocumentContext(@NotNull Editor editor) {
    this.editor = editor;
    this.application = editor.getApplication();
  }


  public @Nullable Document lookup(@NotNull JarFile entry) {
    byte[] graphBinData = readGraphBinData(entry);
    if (graphBinData != null) {
      Graph graph = DocumentGraphCodec.decode(graphBinData);
      graph.initialize();
      return new Document(editor, graph);
    }
    else {
      return null;
    }
  }


  public void put(@NotNull Document document) {
    File entry = new File(document.getEntryFilePath());
    if (entry.exists()) {
      updateDocumentJarFile(document);
    }
    else {
      try {
        createDocumentJarFile(document, entry);
      }
      catch (IOException e) {
        ExceptionManager.INSTANCE.dealWith(e);
      }
    }
  }


  private @Nullable byte[] readGraphBinData(@NotNull JarFile jarFile) {
    ZipEntry graphBinEntry = jarFile.getEntry("document/graph.bin");
    try {
      InputStream inputStream = jarFile.getInputStream(graphBinEntry);
      byte[] bytes = new byte[inputStream.available()];
      int count = inputStream.read(bytes);
      log.debug("read {} bytes from jar entry", count);
      return bytes;
    }
    catch (IOException e) {
      log.debug("{}", e);
      return null;
    }
  }


  @Contract(pure = true)
  private void createDocumentJarFile(@NotNull Document document, @NotNull File entry) throws IOException
  {
    BiPredicate<File, File> isParent = (dir, file) -> false;
    File tempDir = Files.createTempDir();
    File docDir = new File(tempDir, "document");
    if (!docDir.exists()) {
      if (docDir.mkdir()) {
        log.debug("make directory {}", docDir);
      }
    }
    File graphBinFile = new File(docDir, "graph.bin");
    byte[] graphData = DocumentGraphCodec.encode((Graph) document.getGraph());
    Files.write(graphData, graphBinFile);
    Manifest manifestFile = createManifest();
    if (!isParent.test(docDir, entry)) {
      JarUtil.pack(docDir, entry, manifestFile);
      document.getApplication().getConsole().println(MessageBundle.get("document.save.success"));
      log.debug("create document jar file {}", StringUtil.getName(entry));
    }
    else {
      document.getApplication().getConsole().println(MessageBundle.get("document.save.fail"));
      log.error("jar file is under it's archive directory, hence it will not be created");
    }
  }


  private @NotNull Manifest createManifest()
  {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_TITLE, application.getImplementationTitle());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VERSION, application.getImplementationVersion());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VENDOR, application.getImplementationVendor());
    return manifest;
  }


  private void updateDocumentJarFile(@NotNull IDocument document)
  {
    log.debug("update document jar file, {}", document);
  }
}
