package com.lgsim.engine.graphEditor.app;

import com.bulenkov.darcula.DarculaLaf;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.IconBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.widget.console.IConsole;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.action.ApplicationActionMap;
import com.lgsim.engine.graphEditor.util.Configuration;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class Application implements IApplication {

  private static final String corporationName = "LGSimulator";
  private static final String artifactName = "GraphEditor";
  private static final String version = "1.0";
  private static final Configuration CONFIGURATION = new Configuration(corporationName, artifactName, version);
  private final IApplicationAction applicationAction;
  private ISolverEnvironment solverEnvironment;
  private Editor editor;


  private Application() throws InstantiationException
  {
    applicationAction = new ApplicationActionMap();
    mxSwingConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
    mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";

    JFrame frame = new JFrame();
    editor = new Editor(this);
    JMenuBar menuBar = ApplicationSupport.createApplicationMenuBar(this);
    frame.setJMenuBar(menuBar);
    frame.getContentPane().add(editor);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.addWindowListener(new ApplicationListener());
  }


  public static void main(String[] args)
  {
    IconBundle.newInstance().registerAll();
    ResourceRegistry.INSTANCE.registerAll();
    ImplementationRegistry.INSTANCE.registerAll();
    CONFIGURATION.applyIfPossible();
    SwingUtilities.invokeLater(() -> {
      try {
        // workaround due to bug in initializer (bulenkov/Darcula issue #29)
        UIManager.getFont("Label.font");
        UIManager.setLookAndFeel(new DarculaLaf());
        new Application();
      }
      catch (Exception e) {
        ExceptionManager.INSTANCE.dealWith(e);
      }
    });
  }


  @Override
  public @NotNull String getImplementationTitle()
  {
    return artifactName;
  }


  @Override
  public @NotNull String getImplementationVersion()
  {
    return version;
  }


  @Override
  public @NotNull String getImplementationVendor()
  {
    return corporationName;
  }


  @Override
  public @NotNull Configuration getConfiguration() {
    return CONFIGURATION;
  }


  @Override
  public @NotNull IApplicationAction getApplicationAction() {
    return applicationAction;
  }


  @Override
  public @Nullable ISolverEnvironment getSolverEnvironment() {
    return solverEnvironment;
  }


  @Override
  public void setSolverEnvironment(@NotNull ISolverEnvironment environment) {
    this.solverEnvironment = environment;
  }


  @Override
  public @NotNull IConsole getConsole() {
    return editor.getConsole();
  }
}