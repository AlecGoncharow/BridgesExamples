import bridges.connect.Bridges;
import bridges.base.TreeElement;

public class tree{
	public static void main(String[] args) throws Exception{
    	
   					//create the Bridges object
		Bridges bridges = new Bridges(18, "1460086858525", "agoncharow");

   					//create   tree nodes
		TreeElement<String> t0 = new TreeElement<String>("Hello");
		TreeElement<String> t2 = new TreeElement<String>("This");
		TreeElement<String> t3 = new TreeElement<String>("is");
		TreeElement<String> t4 = new TreeElement<String>("a");
		TreeElement<String> t5 = new TreeElement<String>("generic");
		TreeElement<String> t6 = new TreeElement<String>("Tree");
		TreeElement<String> t7 = new TreeElement<String>("Representation");

					// put in labels for each node; we simply use integers 

		t0.setLabel(t0.getValue());
		t2.setLabel(t2.getValue());
		t3.setLabel(t3.getValue());
		t4.setLabel(t4.getValue());
		t5.setLabel(t5.getValue());
		t6.setLabel(t6.getValue());
		t7.setLabel(t7.getValue());

					// add links to children

		t0.addChild(t2);
		t0.addChild(t3);
		t0.addChild(t4);
		t2.addChild(t5);
		t2.addChild(t6);
		t3.addChild(t7);

									// set some visual attributes
		t0.getVisualizer().setColor("red");
		t0.getVisualizer().setOpacity(0.3f);

		bridges.setTitle("A General Tree Example");
             					//set visualizer type
		bridges.setDataStructure(t0);
        						// visualize the tree
		bridges.visualize();
    }
}



