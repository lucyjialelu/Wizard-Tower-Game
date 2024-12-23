package WizardTD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CreateNodes {
    float x, y;
    int cost;
    CreateNodes parent;
    public int heuristicCost;

    public CreateNodes(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.parent = null;
        this.heuristicCost = 0;
    }

    /**
     * Get the Manhattan distance from one node to another
     * @param otherNode, CreateNodes object 
     * @return float, distance
     */
    public float getManhattanDistanceTo(CreateNodes otherNode) {
        return Math.abs(this.x - otherNode.x) + Math.abs(this.y - otherNode.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreateNodes node = (CreateNodes) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Source: https://www.youtube.com/watch?v=PzEWHH2v3TE
     * https://www.redblobgames.com/pathfinding/a-star/introduction.html
     * @param filePath, all file tiles within the map
     * @return List, of CreateNode objects (tile paths) of shortest path 
     * @throws IOException, if there is no starting node or Wizard's house
     */
    public static List<CreateNodes> findShortestPath(String filePath) throws IOException {
        List<CreateNodes> path = new ArrayList<>();
        List<CreateNodes> openSet = new ArrayList<>();
        Set<CreateNodes> closedSet = new HashSet<>();
        List<CreateNodes> possibleStarts = new ArrayList<>();
        List<CreateNodes> inbetweens = new ArrayList<>();

        CreateNodes startNode = null;
        CreateNodes endNode = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int x = 0;
            int y = 40;
            String target = "X";
            String wizard = "W";

            while ((line = reader.readLine()) != null) {
                // node (startNode) can be from first line, last line, or any line that has index 0 char = X
                for (char character : line.toCharArray()) {
                    if (character == target.charAt(0)) {
                        if(x==0 || y==40 || x==608 || y==608){
                            CreateNodes node = new CreateNodes(x, y, 0);
                            possibleStarts.add(node);
                        } 
                        CreateNodes nodesi = new CreateNodes(x, y, 0);
                        inbetweens.add(nodesi);
                    } else if (character == wizard.charAt(0)) {
                        CreateNodes endNode1 = new CreateNodes(x, y, 0);
                        endNode = endNode1;
                    }
                    x += 32;
                }
                y += 32;
                x = 0;
            }

            // randomise a starting point each time
            Random random = new Random();
            int numStarts = possibleStarts.size();
            int randomIndex = random.nextInt(numStarts);
        
            CreateNodes node = possibleStarts.get(randomIndex);
            if (startNode == null) {
                startNode = node;
            }

            path.add(startNode);
            path.addAll(inbetweens);
            path.add(endNode);
        }

        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Start or end node of path found.");
        }

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            CreateNodes currentNode = getLowestCostNode(openSet, endNode);

            if (currentNode.equals(endNode)) {
                return reconstructPath(currentNode);
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            List<CreateNodes> neighbors = getNeighbors(currentNode, path);
            for (CreateNodes neighbor : neighbors) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeCost = currentNode.cost + 1;
                if (!openSet.contains(neighbor) || tentativeCost < neighbor.cost) {
                    neighbor.parent = currentNode;
                    neighbor.cost = tentativeCost;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null; // no path found
    }

    /**
     * Gets the Node of the Lowest Cost
     * @param openSet, List of path tiles 
     * @param endNode, Node of the Wizard's house
     * @return
     */
    private static CreateNodes getLowestCostNode(List<CreateNodes> openSet, CreateNodes endNode) {
        return openSet.stream()
            .min(Comparator.comparingInt(node -> (int) (node.cost + calculateHeuristic(node, endNode))))
            .orElse(null);
    }

    /**
     * 
     * @param node
     * @param endNode
     * @return
     */
    private static int calculateHeuristic(CreateNodes node, CreateNodes endNode) {
        // calculate the Manhattan distance as the heuristic
        return (int) (Math.abs(node.x - endNode.x) + Math.abs(node.y - endNode.y));
    }

    /**
     * Gets neighbouring nodes within the path from given node 
     * @param node, Chosen node to get the neighbouring nodes 
     * @param path, All path tiles within the map
     * @return List, of CreateNodes objects which are all the neighboring nodes
     */
    private static List<CreateNodes> getNeighbors(CreateNodes node, List<CreateNodes> path) {
        List<CreateNodes> neighbors = new ArrayList<>();
        for (CreateNodes otherNode : path) {
            if (node != otherNode && node.getManhattanDistanceTo(otherNode) == 32) {
                neighbors.add(otherNode);
            }
        }
        return neighbors;
    }

    /**
     * Reconstructing the shortest path from the EndNode back by using its parent node.
     * @param endNode, End node of the map, Wizard's house
     * @return List, of CreateNodes objects 
     */
    private static List<CreateNodes> reconstructPath(CreateNodes endNode) {
        List<CreateNodes> path = new ArrayList<>();
        CreateNodes currentNode = endNode;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
