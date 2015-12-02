package naivebayes;

public class ConfusionObject {
	private String prediction;
	private String theClass;

    public ConfusionObject(String prediction, String theClass) {
      this.prediction = prediction;
      this.theClass = theClass;
    }

    public String getPrediction() {
      return this.prediction;
    }
    
    public String getTheClass() {
      return theClass;
    }
    
    @Override
    public int hashCode() {
        return prediction.hashCode() ^ theClass.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof ConfusionObject) 
        && ((ConfusionObject) obj).prediction.equals(prediction)
        && ((ConfusionObject) obj).theClass.equals(theClass);
    }
}
