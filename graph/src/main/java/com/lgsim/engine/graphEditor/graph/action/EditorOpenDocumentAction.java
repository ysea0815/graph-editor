package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

@SuppressWarnings("WeakerAccess")
public class EditorOpenDocumentAction extends EditorAction {

  private static final Logger log = LoggerFactory.getLogger(EditorOpenDocumentAction.class);
  private JFileChooser openLocationChooser;


  public EditorOpenDocumentAction(@NotNull Editor editor) {
    super(editor);
    openLocationChooser = new JFileChooser();
  }


  @Override
  public void actionPerformed(ActionEvent event) {
    int code = openLocationChooser.showOpenDialog(editor.getSwingComponent());
    if (code == JFileChooser.APPROVE_OPTION) {
      File entry = openLocationChooser.getSelectedFile();
      JarFile jarFile = getDocumentJarFile(entry);
      if (jarFile != null) {
        Document document = editor.getDocumentContext().lookup(jarFile);
        if (document != null) {
          editor.addDocument(document);
          document.getSwingComponent().zoomAndCenter();
        }
      }
      else {
        log.debug("open document jar file failed");
      }
    }
  }


  private @Nullable JarFile getDocumentJarFile(@NotNull File entry) {
    if (entry.exists()) {
      try {
        JarFile jarFile = new JarFile(entry);
        if (isValidDocumentJarFile(jarFile.getManifest())) {
          return jarFile;
        }
        else {
          log.debug("not valid document jar file");
          return null;
        }
      }
      catch (IOException e) {
        log.debug("{}", e);
        return null;
      }
    }
    else {
      log.debug("file not exists");
      return null;
    }
  }


  private boolean isValidDocumentJarFile(@NotNull Manifest manifest) {
    IApplication app = editor.getApplication();
    String implementationTitle = app.getImplementationTitle();
    String implementationVersion = app.getImplementationVersion();
    String implementationVendor = app.getImplementationVendor();
    Object title = manifest.getMainAttributes().get(Attributes.Name.IMPLEMENTATION_TITLE);
    Object version = manifest.getMainAttributes().get(Attributes.Name.IMPLEMENTATION_VERSION);
    Object vendor = manifest.getMainAttributes().get(Attributes.Name.IMPLEMENTATION_VENDOR);
    return (implementationTitle.equals(title))
           && (implementationVersion.equals(version))
           && (implementationVendor.equals(vendor));
  }
}
