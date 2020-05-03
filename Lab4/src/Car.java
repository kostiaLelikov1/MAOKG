import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class Car implements ActionListener {
    private TransformGroup carTransformGroup = new TransformGroup();
    private Transform3D carTransform3D = new Transform3D();
    private Timer timer;
    private float angle= 0;

    public static void main(String[] args) {
        new Car();
    }

    private Car() {
        timer = new Timer(50, this);
        timer.start();
        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        carTransformGroup = new TransformGroup();
        carTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildCastleSkeleton();
        objRoot.addChild(carTransformGroup);

        Background background = new Background(new Color3f(1.0f, 1.0f, 1.0f));
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(.8f, .8f, .0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }

    private void buildCastleSkeleton() {
        Cylinder body3 = CarBody.getFrontCylinderPart();
        Transform3D body3T = new Transform3D();
        body3T.rotX(3.14/2);
        body3T.setTranslation(new Vector3f(.55f, .0f, .0f));
        TransformGroup body3TG = new TransformGroup();
        body3TG.setTransform(body3T);
        body3TG.addChild(body3);
        carTransformGroup.addChild(body3TG);

        Cylinder body4 = CarBody.getFrontWindow();
        Transform3D body4T = new Transform3D();
        body4T.rotX(3.14/2);
        body4T.setTranslation(new Vector3f(.55f, .0f, .0f));
        TransformGroup body4TG = new TransformGroup();
        body4TG.setTransform(body4T);
        body4TG.addChild(body4);
        carTransformGroup.addChild(body4TG);

        Box body1 = CarBody.getBackPart();
        Transform3D body1T = new Transform3D();
        body1T.setTranslation(new Vector3f());
        TransformGroup body1TG = new TransformGroup();
        body1TG.setTransform(body1T);
        body1TG.addChild(body1);
        carTransformGroup.addChild(body1TG);

        Box body2 = CarBody.getFrontPart();
        Transform3D body2T = new Transform3D();
        body2T.setTranslation(new Vector3f(.7f, -.15f, .0f));
        TransformGroup body2TG = new TransformGroup();
        body2TG.setTransform(body2T);
        body2TG.addChild(body2);
        carTransformGroup.addChild(body2TG);

        createWheel(0.5f, -0.25f, -0.3f);
        createWheel(0.5f, -0.25f, 0.3f);

        createWheel(-0.2f, -0.25f, -0.3f);
        createWheel(-0.2f, -0.25f, 0.3f);

        TransformGroup tg = new TransformGroup();
        Transform3D transform = new Transform3D();
        transform.rotZ(3.14/2);
        Cylinder Wheel = CarBody.getWheel();
        Vector3f vector = new Vector3f(-0.5f, .0f, -.0f);
        transform.setTranslation(vector);
        tg.setTransform(transform);
        tg.addChild(Wheel);
        carTransformGroup.addChild(tg);
    }

    private void createWheel(float x, float y, float z) {
        TransformGroup tg = new TransformGroup();
        Transform3D transform = new Transform3D();
        transform.rotX(3.14/2);
        Cylinder Wheel = CarBody.getWheel();
        Vector3f vector = new Vector3f(x, y, z);
        transform.setTranslation(vector);
        tg.setTransform(transform);
        tg.addChild(Wheel);
        carTransformGroup.addChild(tg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        carTransform3D.rotY(angle);
        carTransformGroup.setTransform(carTransform3D);
        angle += 0.05;
    }
}