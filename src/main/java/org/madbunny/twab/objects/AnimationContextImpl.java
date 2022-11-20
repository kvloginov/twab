package org.madbunny.twab.objects;

import org.madbunny.twab.objects.animated.AnimationContext;
import org.madbunny.vsrat2d.api.Point2D;

public record AnimationContextImpl(Point2D currentPos, Point2D prevPos) implements AnimationContext {

    @Override
    public Point2D currentPos() {
        return currentPos;
    }

    @Override
    public Point2D prevPos() {
        return prevPos;
    }
}
