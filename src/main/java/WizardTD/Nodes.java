package WizardTD;

public class Nodes {
    float x, y;
    float cost;
    int heuristicCost;

    public Nodes(float x, float y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.heuristicCost = 0;
    }
    /**
     * Gets/calculates Manhattan distnace for use in finding the 
     * shortest path in A* algorithm
     * @param other, CreateNodes object
     * @return int, Manhattan Distance
     */
    public int getManhattanDistanceTo(CreateNodes other) {
        return (int) (Math.abs(this.x - other.x) + Math.abs(this.y - other.y));
    }
}
