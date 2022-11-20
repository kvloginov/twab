package org.madbunny.twab.objects.animated;

import org.madbunny.vsrat2d.api.Point2D;

public interface AnimationContext {
    Point2D currentPos();

    Point2D prevPos();
}
