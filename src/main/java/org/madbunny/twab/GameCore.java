package org.madbunny.twab;

import org.madbunny.twab.objects.Entity;
import org.madbunny.twab.objects.animated.AnimationContext;
import org.madbunny.twab.objects.animated.DisappearingChainElement;
import org.madbunny.twab.objects.plume.Plume;
import org.madbunny.vsrat2d.api.ApplicationContext;
import org.madbunny.vsrat2d.api.Color;
import org.madbunny.vsrat2d.api.FrameContext;
import org.madbunny.vsrat2d.api.Point2D;

import java.util.function.Function;

public class GameCore {
    private static final Color BACKGROUND_COLOR = new Color(0.2f, 0.2f, 0.2f);
    private static final Color TEXT_COLOR = new Color(1, 0, 0);

    private final int windowWidth;
    private final int windowHeight;
    private int mainFontId;

    private Plume plume;

    public GameCore(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void initialize(ApplicationContext ctx) {
        mainFontId = ctx.fonts().createFont("fonts/comic-sans-ms.ttf", 24);
//        Function<AnimationContext, Entity> objectConstructor = (aCtx) -> new DisappearingCircle(aCtx.currentPos(), 30, 10, new Color(0.6f, 0.3f, 0.1f), true);
    Function<AnimationContext, Entity> objectConstructor = (aCtx) -> new DisappearingChainElement(aCtx.prevPos(),aCtx.currentPos(), 30, 10, new Color(0.6f, 0.3f, 0.1f), true);


        plume = new Plume(objectConstructor, 500, 0.5f);
    }

    public void update(FrameContext ctx) {
        plume.Update(ctx);


        ctx.screen().clear(BACKGROUND_COLOR);
        ctx.screen().drawText(mainFontId, new Point2D(windowWidth * 0.5f, windowHeight * 0.5f), TEXT_COLOR, "Hello!!1");
        plume.Draw(ctx.screen());
    }
}
