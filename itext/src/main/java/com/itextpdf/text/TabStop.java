package com.itextpdf.text;

import com.itextpdf.text.pdf.draw.DrawInterface;

public class TabStop {
    public enum Alignment {
        LEFT,
        RIGHT,
        CENTER,
        DECIMAL
    }
    protected float position;
    protected Alignment alignment = Alignment.LEFT;
    protected DrawInterface leader;

    public TabStop(float position) {
        this.position = position;
    }

    public TabStop(float position, DrawInterface leader) {
        this(position);
        this.leader = leader;
    }

    public TabStop(float position, DrawInterface leader, Alignment alignment) {
        this(position, leader);
        this.alignment = alignment;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public DrawInterface getLeader() {
        return leader;
    }

    public void setLeader(DrawInterface leader) {
        this.leader = leader;
    }

    public static TabStop computeTabPosition(float currentPosition, float tabInterval, java.util.List<TabStop> tabStops) {
        TabStop tabStop = null;
        if (tabStops != null) {
            for (TabStop currentTabStop : tabStops) {
                if (currentTabStop.getPosition() - currentPosition > 0.001) {
                    tabStop = currentTabStop;
                    break;
                }
            }
        }

        if (tabStop == null) {
            currentPosition = (float)Math.round(currentPosition * 1000) / 1000;
            tabInterval = (float)Math.round(tabInterval * 1000) / 1000;

            tabStop = new TabStop(currentPosition + tabInterval - currentPosition % tabInterval);
        }

        return tabStop;
    }
}