package dmfmm.starvationahoy.api.Meat;


import net.minecraft.util.math.AxisAlignedBB;

/**
 *
 */
public interface ISAModel {

    /**
     * Function called while Model is on meat Hanger.
     * Used to allow modders to make tranformations to the model with GL
     */
    public void glTransformations();

    /**
     * Function called before glTransformations, should be used to rotate model specific parts.
     * EG. ModelPig.leg4.rotateAngleX = -77F;
     *
     */
    public void modelTransformations();

    /**
     * Function used to render AABB around Model while on meat Hanger.
     *
     * @return AABB that encompasses entity
     */
     AxisAlignedBB getMeatAABB(double x, double minX, double maxX, double y, double minY, double maxY, double z, double minZ, double maxZ);
}
