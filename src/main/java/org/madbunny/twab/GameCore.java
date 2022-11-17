package org.madbunny.twab;

import org.madbunny.vsrat2d.api.ApplicationContext;
import org.madbunny.vsrat2d.api.Color;
import org.madbunny.vsrat2d.api.FrameContext;
import org.madbunny.vsrat2d.api.Point2D;

public class GameCore {
    private static final Color BACKGROUND_COLOR = new Color(0.2f, 0.2f, 0.2f);
    private static final Color TEXT_COLOR = new Color(1, 0, 0);

    private final int windowWidth;
    private final int windowHeight;
    private int mainFontId;

    public GameCore(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void initialize(ApplicationContext ctx) {
        mainFontId = ctx.fonts().createFont("fonts/comic-sans-ms.ttf", 24);
    }

    public void update(FrameContext ctx) {
        ctx.screen().clear(BACKGROUND_COLOR);
        ctx.screen().drawText(mainFontId, new Point2D(windowWidth * 0.5f, windowHeight * 0.5f), TEXT_COLOR, "Hello!!1");
    }
}
