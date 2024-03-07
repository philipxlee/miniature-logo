package slogo.model.api.parser.nodes;

import java.util.List;

public class ListNode extends ASTNode {
    public ListNode(List<ASTNode> initialChildren) {
        super(); // Ensure the base constructor is called to initialize `children`
        for (ASTNode child : initialChildren) {
            this.addChild(child); // Use `addChild` to ensure any subclass-specific behavior is respected
        }
    }
}