package lox;

import java.util.List;

public class Dependency implements Stmt.Visitor<DependencyAssignment>, Expr.Visitor<DependencyAssignment> {
    public void getDependencies(List<Stmt> statements) {
        for (Stmt stmt : statements) {
            getDependencies(stmt);
        }
    }

    public void getDependencies(Stmt stmt) {
        stmt.accept(this);
    }

    @Override
    public DependencyAssignment visitBlockStmt(Stmt.Block stmt) {
        for (Stmt individual : stmt.statements) {
            stmt.da.addDependencyAssignment(individual.accept(this));
        }
        return stmt.da;
    }

    @Override
    public DependencyAssignment visitExpressionStmt(Stmt.Expression stmt) {
        stmt.da.addDependencyAssignment(stmt.expression.accept(this));
        return stmt.da;
    }

    @Override
    public DependencyAssignment visitIfStmt(Stmt.If stmt) {
        stmt.da.addDependencyAssignment(stmt.condition.accept(this));
        stmt.da.addDependencyAssignment(stmt.thenBranch.accept(this));
        stmt.da.addDependencyAssignment(stmt.elseBranch.accept(this));
        return stmt.da;
    }

    @Override
    public DependencyAssignment visitPrintStmt(Stmt.Print stmt) {
        stmt.da.addDependencyAssignment(stmt.expression.accept(this));
        return stmt.da;
    }

    @Override
    public DependencyAssignment visitVarStmt(Stmt.Var stmt) {
        stmt.da.addAssigngment(stmt.name.lexeme);
        stmt.da.addDependencyAssignment(stmt.initializer.accept(this));
        return stmt.da;
    }

    @Override
    public DependencyAssignment visitWhileStmt(Stmt.While stmt) {
        stmt.da.addDependencyAssignment(stmt.condition.accept(this));
        stmt.da.addDependencyAssignment(stmt.body.accept(this));
        return stmt.da;
    }

    @Override
    public DependencyAssignment visitAssignExpr(Expr.Assign expr) {
        expr.da.addAssigngment(expr.name.lexeme);
        expr.da.addDependencyAssignment(expr.value.accept(this));
        return expr.da;
    }

    @Override
    public DependencyAssignment visitBinaryExpr(Expr.Binary expr) {
        expr.da.addDependencyAssignment(expr.left.accept(this));
        expr.da.addDependencyAssignment(expr.right.accept(this));
        return expr.da;
    }

    @Override
    public DependencyAssignment visitGroupingExpr(Expr.Grouping expr) {
        expr.da.addDependencyAssignment(expr.expression.accept(this));
        return expr.da;
    }

    @Override
    public DependencyAssignment visitLiteralExpr(Expr.Literal expr) {
        return new DependencyAssignment();
    }

    @Override
    public DependencyAssignment visitLogicalExpr(Expr.Logical expr) {
        expr.da.addDependencyAssignment(expr.left.accept(this));
        expr.da.addDependencyAssignment(expr.right.accept(this));
        return expr.da;
    }

    @Override
    public DependencyAssignment visitUnaryExpr(Expr.Unary expr) {
        expr.da.addDependencyAssignment(expr.right.accept(this));
        return expr.da;
    }

    @Override
    public DependencyAssignment visitTernaryExpr(Expr.Ternary expr) {
        expr.da.addDependencyAssignment(expr.condition.accept(this));
        expr.da.addDependencyAssignment(expr.thenExpr.accept(this));
        expr.da.addDependencyAssignment(expr.elseExpr.accept(this));
        return expr.da;
    }

    @Override
    public DependencyAssignment visitVariableExpr(Expr.Variable expr) {
        expr.da.addDependency(expr.name.lexeme);
        return expr.da;
    }
}
