package com.daniel.设计模式.创建者模式.工厂方法模式;

public abstract class Dialog {

    public void renderWindow() {
        // ... other code ...
        System.out.println("渲染窗口");
        Button okButton = createButton();
        okButton.render();
    }

    /**
     * Subclasses will override this method in order to create specific button
     * objects.
     */
    public abstract Button createButton();
}