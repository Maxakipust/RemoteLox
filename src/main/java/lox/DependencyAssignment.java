package lox;

import java.util.HashSet;
import java.util.Set;

public class DependencyAssignment {
    Set<String> dependencies = new HashSet<>();
    Set<String> assignments = new HashSet<>();

    public void addDependency(String dependency) {
        this.dependencies.add(dependency);
    }

    public void addDependencies(Set<String> dependencies) {
        this.dependencies.addAll(dependencies);
    }

    public void addAssigngment(String assignment) {
        this.assignments.add(assignment);
    }

    public void addAssignments(Set<String> assignments) {
        this.assignments.addAll(assignments);
    }

    public void addDependencyAssignment(DependencyAssignment da) {
        this.assignments.addAll(da.assignments);
        this.dependencies.addAll(da.dependencies);
    }
}
