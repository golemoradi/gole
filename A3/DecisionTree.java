//Gole Moradi
//260726494
import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf



		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {
			 if (datalist==null) {
				 return this;
			 }
			 
			 int size = datalist.size();
			 
			 //checking the labelled data set has at least k data items (minSize)
			 if (minSizeDatalist<=size) {
				 //checking if all data items have the same label
				 boolean check = true;
				 Datum prev = null;
				 int counter = 0;
				 for (Datum d: datalist ) {
					 if (counter!=0) {
						 if (d.y!=prev.y) {
						 check = false;
						 }
					 }
					 prev = d;
					 counter ++;
				 } 
				 
				 //creating leaf node
				 if (check) {
					 this.leaf = true;
					 this.label = datalist.get(0).y;
					 return this;
				 } else {
					 //Creating the best attribute test question 
					 double best_entropy = Double.MAX_VALUE;
					 double best_threshold = -1;
					 int best_attribute = -1;
					 
					 for (int attribute=0; attribute<2; attribute++) {
						 for (Datum data1: datalist) {
							 ArrayList<Datum> left2 = new ArrayList<Datum>();
							 ArrayList<Datum> right2 = new ArrayList<Datum>();
							 for(Datum data2: datalist) {
								 //splitting 
								 if(data1.x[attribute]>data2.x[attribute]) {
									left2.add(data2);
								 } else {
									right2.add(data2);	
								 }
							 }
							 //calculating the entropy of the two lists
							 int left_size=left2.size();
							 int right_size=right2.size();
							 double left_entropy = calcEntropy(left2);
							 double right_entropy = calcEntropy(right2);
							 double avg_entropy = ((left_size*left_entropy)+(right_size*right_entropy))/(left_size+right_size);
							 
							 if (avg_entropy<best_entropy) {
								 best_entropy = avg_entropy;
								 best_threshold = data1.x[attribute];
								 best_attribute = attribute;
							 } 	 		
						 }
					 }
					 
					 //splitting the datum
					 ArrayList<Datum> left1 = new ArrayList<Datum>();
					 ArrayList<Datum> right1 = new ArrayList<Datum>();
					 for (Datum dat: datalist) {
						 if (dat.x[best_attribute]<best_threshold) {
							 left1.add(dat);
						 } else {
							 right1.add(dat);
						 }
					 }
					 
					 this.threshold = best_threshold;
					 this.attribute = best_attribute;
					 this.leaf = false;
					 this.left = new DTNode();
					 this.left = fillDTNode(left1);
					 this.right = new DTNode();
					 this.right = fillDTNode(right1);
				 }
			 }
		
			return this; 
		}



		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			//if (xQuery==null) {
			//	return -1;
			//}
			if (this.leaf) {
				return this.label;
			}
			if (xQuery[this.attribute]<this.threshold) {
				return this.left.classifyAtNode(xQuery);
			}
			if (xQuery[this.attribute]>=this.threshold) {
				return this.right.classifyAtNode(xQuery);
			}
			return -1; //dummy code.  Update while completing the assignment.
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{
			//casting dt2 into an object of DTNode so it can be compared
			DTNode node = (DTNode) dt2; 
			
			//checking if the rooted trees are equal
			if (this!=null && node!=null) {
				//if they are leaves
				if (this.leaf && node.leaf) {
					if (this.label==node.label) {
						return true;
					}
					
					//if they are internal nodes
				} else if (!this.leaf && !node.leaf) {
					if (this.attribute==node.attribute && this.threshold==node.threshold) {
						boolean check = true; 
						if (this.left==null) {
							if (node.left!=null) {
								check = false;
							}
						}
						if (check && this.right==null) {
							if (node.right!=null) {
								check = false;
							}
						}
						if (check && this.left!=null) {
							if (!this.left.equals(node.left)) {
								check = false;
							}
						}
						if (check && this.right!=null) {
							if (!this.right.equals(node.right)) {
								check = false;
							}
						}
						return check;
					}
				}
			}
			return false; 
		}
	}



	//Given a dataset, this retuns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

} 


