package com.deckerben.minecraft.laf;

import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

public class MinecraftUI extends BasicLookAndFeel {

    private final String mcLafPkg = "com.deckerben.minecraft.laf.";
    private final String mcComponentPkg = "ui.";
    private final String mcBorderPkg = "borders.";

    public MinecraftUI() {

    }

    //Overrides aus
    //LookAndFeel
    @Override
    public String getName() {
        return "MinecraftUI";
    }

    @Override
    public String getDescription() {
        return "A Minecraft UI component mimicking Look-And-Feel.";
    }

    @Override
    public String getID() {
        return "Minecraft";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }

    @Override
    protected void initClassDefaults(UIDefaults table) {
        super.initClassDefaults(table);
        final String basicPackageName = "javax.swing.plaf.basic.";

        Object[] uiDefaults = {
                "ButtonUI", mcLafPkg + mcComponentPkg + "McButtonUI",
                //"CheckBoxUI", basicPackageName + "BasicCheckBoxUI",
                //"ColorChooserUI", basicPackageName + "BasicColorChooserUI",
                //"FormattedTextFieldUI", basicPackageName + "BasicFormattedTextFieldUI",
                //"MenuBarUI", basicPackageName + "BasicMenuBarUI",
                //"MenuUI", basicPackageName + "BasicMenuUI",
                //"MenuItemUI", basicPackageName + "BasicMenuItemUI",
                //"CheckBoxMenuItemUI", basicPackageName + "BasicCheckBoxMenuItemUI",
                //"RadioButtonMenuItemUI", basicPackageName + "BasicRadioButtonMenuItemUI",
                //"RadioButtonUI", basicPackageName + "BasicRadioButtonUI",
                "ToggleButtonUI", mcLafPkg + mcComponentPkg + "McToggleButtonUI",
                //"PopupMenuUI", basicPackageName + "BasicPopupMenuUI",
                //"ProgressBarUI", basicPackageName + "BasicProgressBarUI",
                //"ScrollBarUI", basicPackageName + "BasicScrollBarUI",
                //"ScrollPaneUI", basicPackageName + "BasicScrollPaneUI",
                //"SplitPaneUI", basicPackageName + "BasicSplitPaneUI",
                //"SliderUI", basicPackageName + "BasicSliderUI",
                //"SeparatorUI", basicPackageName + "BasicSeparatorUI",
                //"SpinnerUI", mcLafPkg + mcComponentPkg + "McSpinnerUI",
                //"ToolBarSeparatorUI", basicPackageName + "BasicToolBarSeparatorUI",
                //"PopupMenuSeparatorUI", basicPackageName + "BasicPopupMenuSeparatorUI",
                //"TabbedPaneUI", basicPackageName + "BasicTabbedPaneUI",
                //"TextAreaUI", basicPackageName + "BasicTextAreaUI",
                //"TextFieldUI", basicPackageName + "BasicTextFieldUI",
                //"PasswordFieldUI", basicPackageName + "BasicPasswordFieldUI",
                //"TextPaneUI", basicPackageName + "BasicTextPaneUI",
                //"EditorPaneUI", basicPackageName + "BasicEditorPaneUI",
                //"TreeUI", basicPackageName + "BasicTreeUI",
                //"LabelUI", mcLafPkg + mcComponentPkg + "McLabelUI",
                //"ListUI", basicPackageName + "BasicListUI",
                //"ToolBarUI", basicPackageName + "BasicToolBarUI",
                //"ToolTipUI", basicPackageName + "BasicToolTipUI",
                //"ComboBoxUI", basicPackageName + "BasicComboBoxUI",
                //"TableUI", basicPackageName + "BasicTableUI",
                //"TableHeaderUI", basicPackageName + "BasicTableHeaderUI",
                "InternalFrameUI", mcLafPkg + mcComponentPkg + "McInternalFrameUI",
                //"DesktopPaneUI", mcLafPkg + mcComponentPkg + "McDesktopPaneUI",
                "DesktopIconUI", mcLafPkg + mcComponentPkg + "McDesktopIconUI",
                //"FileChooserUI", basicPackageName + "BasicFileChooserUI",
                //"OptionPaneUI", basicPackageName + "BasicOptionPaneUI",
                //"PanelUI", mcLafPkg + mcComponentPkg + "McPanelUI",
                //"ViewportUI", basicPackageName + "BasicViewportUI",
                "RootPaneUI", mcLafPkg + mcComponentPkg + "McRootPaneUI",
        };

        table.putDefaults(uiDefaults);
    }

    @Override
    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);

        final String borderPkg = mcLafPkg + mcComponentPkg + mcBorderPkg;

        ExpandableBorder expandableInternalFrameBorder = new ExpandableBorder(false) {
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        };
        expandableInternalFrameBorder.setBorderTexType(McComponentTextureEnum.PANEL_THICK);
        //BorderUIResource internalFrameBorder = new BorderUIResource(expandableInternalFrameBorder);
        BorderUIResource internalFrameBorder = new BorderUIResource(BorderFactory.createEmptyBorder());

        ExpandableBorder expandabelTextfieldBorder = new ExpandableBorder(false) {
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        };
        expandabelTextfieldBorder.setBorderTexType(McComponentTextureEnum.TEXTFIELD);
        BorderUIResource textfieldBorder = new BorderUIResource(expandabelTextfieldBorder);

        /*ExpandableBorder expandabelSpinnerBorder = new ExpandableBorder(false) {
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        };
        expandabelSpinnerBorder.setBorderTexType(McComponentTextureEnum.TEXTFIELD);
        BorderUIResource spinnerBorder = new BorderUIResource(expandabelSpinnerBorder);*/

        FontUIResource defaultFont = new FontUIResource(McUtils.getMcFont().deriveFont(20f));

        Object[] defaults = {
                "Button.border", BorderFactory.createEmptyBorder(),
                "Button.font", defaultFont,
                "Button.background", ExpandableBorder.getCenterColor(McComponentTextureEnum.BUTTON),
                "Button.disabledBackground", ExpandableBorder.getCenterColor(McComponentTextureEnum.BUTTON_DISABLED),

                "Panel.font", defaultFont,
                //"Panel.background", ExpandableBorder.getFillingColor(McComponentTextureEnum.PANEL_THICK),
                //"Panel.border", panelBorder,

                "ToggleButton.border", BorderFactory.createEmptyBorder(),
                "ToggleButton.font", defaultFont,
                "ToggleButton.background", ExpandableBorder.getCenterColor(McComponentTextureEnum.BUTTON),
                "ToggleButton.selectedBackground", ExpandableBorder.getCenterColor(McComponentTextureEnum.TOGGLEBUTTON_SELECTED),
                "ToggleButton.disabledBackground", ExpandableBorder.getCenterColor(McComponentTextureEnum.BUTTON_DISABLED),

                "Label.font", defaultFont,
                "Label.foreground", new ColorUIResource(Color.WHITE),
                //"Label.border", labelBorder,
                //"Label.background", ExpandableBorder.getCenterColor(McComponentTextureEnum.TEXTFIELD),
                //"Label.iconBackgroundType", McComponentTextureEnum.PANEL_THIN.name(),

                "Spinner.font", defaultFont,
                //"Spinner.border", textfieldBorder,
                //"Spinner.background", ExpandableBorder.getCenterColor(McComponentTextureEnum.TEXTFIELD),
                "Spinner.foreground", new ColorUIResource(Color.WHITE),

                "InternalFrame.border", internalFrameBorder,
                "InternalFrame.borderColor", new ColorUIResource(0,0,0),
                "InternalFrame.titleFont", defaultFont,
                "InternalFrame.background", ExpandableBorder.getFillingColor(McComponentTextureEnum.PANEL_THICK),

                "DesktopIcon.border", null,
        };

        table.putDefaults(defaults);
    }

    protected void initSystemColorDefaults(UIDefaults table) {
        String[] colors = {
                "controlText", "#FFFFFF",
                "controlTextDisabled", "#A0A0A0"
        };
        loadSystemColors(table, colors, false);
    }


}
