
#include <string>
#include "Bridges.h"
#include "DataSource.h"
#include "data_src/EarthquakeUSGS.h"
#include "BSTElement.h"


using namespace std;
using namespace bridges;

BSTElement<float, EarthquakeUSGS> *insert (BSTElement<float, EarthquakeUSGS> *rt,
                    BSTElement<float, EarthquakeUSGS> *new_el);

int max_quakes = 25;

int main() {
	Bridges::initialize(49, "agoncharow", "549234500406");
								// read the earth quake  data and build the BST
	Bridges::setTitle("Recent Earthquakes (USGIS Data)");

    vector<EarthquakeUSGS> eq_list = DataSource::getEarthquakeData(max_quakes);

	BSTElement<float, EarthquakeUSGS> *root = nullptr;

	for (int k = 0; k < max_quakes; k++) {
		EarthquakeUSGS eq = eq_list[k];
		BSTElement<float, EarthquakeUSGS> 
			*bst_node = new BSTElement<float, EarthquakeUSGS>(eq.getMagnitude(), eq); 
			bst_node->setLabel(eq.getTitle() + "\\nLat/Long: ( " +
				to_string(eq.getLatit()) + "," + to_string(eq.getLongit()) + " )\\n" +
                eq.getDateStr());
		root = insert (root, bst_node);
	}

					// set the root to be red
	root->getVisualizer()->setColor(Color("red");
					// visualize the binary search tree
	Bridges::setDataStructure(root);
	Bridges::visualize();

	return 0;
}

BSTElement<float, EarthquakeUSGS> *insert (BSTElement<float, EarthquakeUSGS> *rt,
                    BSTElement<float, EarthquakeUSGS> *new_el) {
	if (rt == nullptr)
		return new_el;
	else if (new_el->getKey() < rt->getKey())
		rt->setLeft(insert(rt->getLeft(), new_el));
	else
		rt->setRight(insert(rt->getRight(), new_el));

	return rt;
}


