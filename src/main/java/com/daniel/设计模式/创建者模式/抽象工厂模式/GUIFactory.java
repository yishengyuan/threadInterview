package com.daniel.设计模式.创建者模式.抽象工厂模式;

/**
 * Abstract factory knows about all (abstract) product types.
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}