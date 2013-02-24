package com.vitco.engine.data.container;

import java.awt.*;
import java.io.Serializable;

/**
 * A Voxel instance, only getter are available (!)
 */
public final class Voxel implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int[] posI;
    private final float[] posF = new float[3]; // position
    public final int id; // id
    private Color color; // color of voxel
    private int alpha = -1; // alpha of this voxel
    private final int layerId; // the id of the layer this voxel lives in
    private int[] textureIds = null; // get the texture ids of this voxel (for all sides)

    private int[] sideRotation = null;
    private boolean[] sideFlip = null;

    // constructor (with texture)
    public Voxel(int id, int[] pos, Color color, boolean selected, int[] textureIds, int layerId) {
        this.id = id;
        this.color = color;
        this.layerId = layerId;
        this.textureIds = textureIds == null ? null : textureIds.clone();
        this.selected = selected;
        posI = pos.clone();
        for (int i = 0; i < pos.length; i++) {
            this.posF[i] = pos[i];
        }
    }

    // retrieve position
    public final int[] getPosAsInt() {
        return posI.clone();
    }
    public final float[] getPosAsFloat() {
        return posF.clone();
    }
    public final String getPosAsString() {
        return posI[0] + "_" + posI[1] + "_" + posI[2];
    }

    // set the color of this voxel
    protected final void setColor(Color color) {
        this.color = color;
    }

    // get the color of this voxel
    public final Color getColor() {
        return color;
    }

    // rotate this voxel
    public final void rotate(Integer side) {
        if (sideRotation == null) {
            sideRotation = new int[6];
        }
        sideRotation[side] = (sideRotation[side] + 1)%4;
    }

    // rotate this voxel (reverse)
    public final void rotateReverse(Integer side) {
        if (sideRotation == null) {
            sideRotation = new int[6];
        }
        sideRotation[side] = (sideRotation[side] + 3)%4;
    }

    // get the rotation of this voxel
    public final int[] getRotation() {
        return sideRotation == null ? null : sideRotation.clone();
    }

    // set the flip of this voxel
    public final void flip(Integer side) {
        if (sideFlip == null) {
            sideFlip = new boolean[6];
        }
        sideFlip[side] = !sideFlip[side];
    }

    // get the flip of this voxel
    public final boolean[] getFlip() {
        return sideFlip == null ? null : sideFlip.clone();
    }

    // get the texture of this voxel
    public final int[] getTexture() {
        return textureIds == null ? null : textureIds.clone();
    }

    // set the alpha of this voxel
    public final void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    // get the color of this voxel
    public final int getAlpha() {
        return alpha;
    }

    // get the layerId of this voxel
    public final int getLayerId() {
        return layerId;
    }

    // ===================================
    // for this object instance only
    private transient boolean selected = false;

    public final boolean isSelected() {
        return selected;
    }

    public final void setSelected(boolean b) {
        selected = b;
    }

}