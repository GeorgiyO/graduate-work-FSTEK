package ru.sstu.ifbs.backoffice.collectors;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.BoxLayout;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.HBoxLayout;
import org.springframework.context.annotation.Scope;

@org.springframework.stereotype.Component
@Scope("prototype")
public class HBoxCollector extends BoxCollector<HBoxLayout> {
    public HBoxCollector() {
        super(HBoxLayout.class);
    }
}
