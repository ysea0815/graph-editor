package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Hashtable;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ApplicationActionMap implements IApplicationAction {

  private final Map<String, Action> actionMap = new Hashtable<>();


  public ApplicationActionMap() {
  }


  @Override
  public @NotNull Action getVertexCellCopyAction() {
    return get("vertexCell.copy");
  }


  @Override
  public @NotNull Action getVertexCellPasteAction() {
    return get("vertexCell.paste");
  }


  @Override
  public @NotNull Action getVertexCellDeleteAction() {
    return get("vertexCell.delete");
  }


  @Override
  public @NotNull Action getVertexCellCutAction() {
    return get("vertexCell.cut");
  }


  @Override
  public @NotNull Action getEditorNewDocumentAction() {
    return actionMap.get("editor.newDocument");
  }


  @Override
  public @NotNull Action getEditorOpenDocumentAction() {
    return actionMap.get("editor.openDocument");
  }


  @Override
  public @NotNull Action getEditorCloseDocumentAction() {
    return actionMap.get("editor.closeDocument");
  }


  @Override
  public @NotNull Action getEditorSaveDocumentAction() {
    return actionMap.get("editor.saveDocument");
  }


  @Override
  public @NotNull Action getSolverCalcAction() {
    return actionMap.get("solver.calc");
  }


  @Override
  public @NotNull Action getSolverSettingAction() {
    return actionMap.get("solver.setting");
  }


  @Override
  public @NotNull Action getApplicationExitAction() {
    return new ApplicationExitAction();
  }


  @Override
  public @NotNull Action getStandardAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getLayoutAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getMoveAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getFormatAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getToolAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getViewAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getControlAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getBankAction() {
    return ActionSupport.emptyAction();
  }


  @Override
  public @NotNull Action getCustomAction() {
    return ActionSupport.emptyAction();
  }


  public @NotNull Action get(@NotNull String k) {
    Action action = actionMap.get(k);
    if (action == null) {
      return ActionSupport.emptyAction();
    }
    else {
      return action;
    }
  }


  public void put(@NotNull String k, @NotNull Action action) {
    actionMap.put(k, action);
  }
}
