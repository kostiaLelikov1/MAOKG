package ua.kpi.fpm.pzks.maokg;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Amanita extends Applet implements ActionListener {

    private final TransformGroup amanitaTransformGroup = new TransformGroup();
    private final Transform3D amanitaTransform3d = new Transform3D();
    private final Timer timer = new Timer(50, this);
    private float angle = 0;
    private double scale = 0;
    private boolean rotateY = true;
    private boolean isDecreasing = false;

    public static void main(String[] args) {
        var obj = new Amanita();
        MainFrame mf = new MainFrame(obj, 700, 700);
        mf.run();
    }

    private Amanita() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(config);
        add("Center", c);
        SimpleUniverse universe = new SimpleUniverse(c);

        timer.start();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(createSceneGraph());
    }

    private BranchGroup createSceneGraph() {
        var root = new BranchGroup();

        amanitaTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(amanitaTransformGroup);
        buildAmanita();

        TextureLoader loader = new TextureLoader("/home/andrewms/IdeaProjects/labs-graphics/graphics-lab-4/assets/zagadochni_les.jpg", "LUMINANCE", new Container());
        var texture = loader.getImage();

        Background background = new Background(texture);
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        background.setCapability(Background.ALLOW_IMAGE_WRITE);
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        root.addChild(background);

        // light section start
        var bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100);

        var sunLightColor = new Color(242, 255, 0);
        var lightDirect = new DirectionalLight(new Color3f(sunLightColor), new Vector3f(0, 0, 0));
        lightDirect.setInfluencingBounds(bounds);
        root.addChild(lightDirect);

        var ambientLightNode = new AmbientLight(new Color3f(new Color(100, 255, 255)));
        ambientLightNode.setInfluencingBounds(bounds);
        root.addChild(ambientLightNode);
        // light section end

        return root;
    }


    private void buildAmanita() {

        TransformGroup amanitaLegGroup = new TransformGroup();
        Transform3D transform = new Transform3D();
        Cylinder cylinder = AmanitaBody.getCylinder(0.1f, 0.6f);
        Vector3f vector = new Vector3f(.0f, -0.4f, .0f);
        transform.setTranslation(vector);
        amanitaLegGroup.setTransform(transform);
        amanitaLegGroup.addChild(cylinder);
        amanitaTransformGroup.addChild(amanitaLegGroup);

        TransformGroup tgMiddle = new TransformGroup();
        Transform3D transform1 = new Transform3D();
        Cylinder cylinder1 = AmanitaBody.getCylinderMiddle(0.6f, 0.2f);
        Vector3f vector1 = new Vector3f(.0f, .0f, .0f);
        transform.setTranslation(vector1);
        tgMiddle.setTransform(transform1);
        tgMiddle.addChild(cylinder1);
        amanitaTransformGroup.addChild(tgMiddle);

        TransformGroup tgTop = new TransformGroup();
        Transform3D transform2 = new Transform3D();
        Cone cone = AmanitaBody.getCone(0.4f, 0.6f);
        Vector3f vector2 = new Vector3f(0.f, 0.2f, 0.f);
        transform2.setTranslation(vector2);
        tgTop.setTransform(transform2);
        tgTop.addChild(cone);
        amanitaTransformGroup.addChild(tgTop);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        amanitaTransform3d.rotX(angle);
        angle += 0.05;
        if (angle >= 25) {
            rotateY = !rotateY;
            angle = 0;
        }


        amanitaTransformGroup.setTransform(amanitaTransform3d);
    }
}