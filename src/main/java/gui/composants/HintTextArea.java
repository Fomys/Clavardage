package gui.composants;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

class HintTextArea extends JTextArea implements FocusListener {

  private final String hint;
  private boolean showingHint;

  public HintTextArea(final String hint) {
    super(hint);
    this.hint = hint;
    this.showingHint = true;
    this.setForeground(new Color(130,130,130));
    super.addFocusListener(this);
  }

  @Override
  public void focusGained(FocusEvent e) {
    if(this.getText().isEmpty()) {
      super.setText("");
      this.setForeground(new Color(230,230,230));
      showingHint = false;
    }
  }
  @Override
  public void focusLost(FocusEvent e) {
    if(this.getText().isEmpty()) {
      super.setText(hint);
      this.setForeground(new Color(130,130,130));
      showingHint = true;
    }
  }

  @Override
  public String getText() {
    return showingHint ? "" : super.getText();
  }
}