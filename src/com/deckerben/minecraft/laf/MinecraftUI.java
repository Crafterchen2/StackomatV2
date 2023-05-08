package com.deckerben.minecraft.laf;

import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.minecraft.laf.textures.borders.McButtonBorder;

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

        BorderUIResource buttonBorder = new BorderUIResource(new McButtonBorder(false){
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        });

        ExpandableTexture expandableInternalFrameBorder = new ExpandableTexture(false) {
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        };
        expandableInternalFrameBorder.setBorderTexType(McComponentTextureEnum.PANEL_THICK);
        //BorderUIResource internalFrameBorder = new BorderUIResource(expandableInternalFrameBorder);
        BorderUIResource internalFrameBorder = new BorderUIResource(BorderFactory.createEmptyBorder());

        ExpandableTexture expandabelTextfieldBorder = new ExpandableTexture(false) {
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        };
        expandabelTextfieldBorder.setBorderTexType(McComponentTextureEnum.TEXTFIELD);
        BorderUIResource textfieldBorder = new BorderUIResource(expandabelTextfieldBorder);

        /*ExpandableTexture expandabelSpinnerBorder = new ExpandableTexture(false) {
            @Override
            protected boolean globalScaleUpdated() {
                return true;
            }
        };
        expandabelSpinnerBorder.setBorderTexType(McComponentTextureEnum.TEXTFIELD);
        BorderUIResource spinnerBorder = new BorderUIResource(expandabelSpinnerBorder);*/

        FontUIResource defaultFont = new FontUIResource(McUtils.getMcFont().deriveFont(20f));

        Object[] defaults = {
                "Button.border", buttonBorder,
                "Button.font", defaultFont,
                "Button.background", ExpandableTexture.getCenterColor(McComponentTextureEnum.BUTTON),
                "Button.disabledBackground", ExpandableTexture.getCenterColor(McComponentTextureEnum.BUTTON_DISABLED),

                "Panel.font", defaultFont,
                //"Panel.background", ExpandableTexture.getFillingColor(McComponentTextureEnum.PANEL_THICK),
                //"Panel.border", panelBorder,

                "ToggleButton.border", buttonBorder,
                "ToggleButton.font", defaultFont,
                "ToggleButton.background", ExpandableTexture.getCenterColor(McComponentTextureEnum.BUTTON),
                "ToggleButton.selectedBackground", ExpandableTexture.getCenterColor(McComponentTextureEnum.TOGGLEBUTTON_SELECTED),
                "ToggleButton.disabledBackground", ExpandableTexture.getCenterColor(McComponentTextureEnum.BUTTON_DISABLED),

                "Label.font", defaultFont,
                "Label.foreground", new ColorUIResource(Color.WHITE),
                //"Label.border", labelBorder,
                //"Label.background", ExpandableTexture.getCenterColor(McComponentTextureEnum.TEXTFIELD),
                //"Label.iconBackgroundType", McComponentTextureEnum.PANEL_THIN.name(),

                "Spinner.font", defaultFont,
                //"Spinner.border", textfieldBorder,
                //"Spinner.background", ExpandableTexture.getCenterColor(McComponentTextureEnum.TEXTFIELD),
                "Spinner.foreground", new ColorUIResource(Color.WHITE),

                "InternalFrame.border", internalFrameBorder,
                "InternalFrame.borderColor", new ColorUIResource(0,0,0),
                "InternalFrame.titleFont", defaultFont,
                "InternalFrame.background", ExpandableTexture.getFillingColor(McComponentTextureEnum.PANEL_THICK),

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
