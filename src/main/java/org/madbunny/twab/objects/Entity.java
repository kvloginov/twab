package org.madbunny.twab.objects;

import org.madbunny.vsrat2d.api.FrameContext;
import org.madbunny.vsrat2d.api.Screen;

public interface Entity {
    void Update(FrameContext ctx);

    void Draw(Screen screen);
}
