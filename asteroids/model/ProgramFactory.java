package asteroids.model;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

import java.util.List;

import asteroids.model.program.*;
import asteroids.model.program.statement.*;
import asteroids.model.program.expression.*;

public class ProgramFactory implements IProgramFactory <Expression, Statement, Function, Program>{

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
		AssignmentStatement assignmentStatement = new AssignmentStatement(sourceLocation);
		return assignmentStatement;
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		WhileStatement whileStatement = new WhileStatement(sourceLocation, condition, body);
		return whileStatement;
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		BreakStatement breakStatement = new BreakStatement(sourceLocation);
		return breakStatement;
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		ReturnStatement returnStatement = new ReturnStatement(sourceLocation, value);
		return returnStatement;
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		IfStatement ifStatement = new IfStatement(sourceLocation, condition, ifBody, elseBody);
		return ifStatement;
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		PrintStatement printStatement = new PrintStatement(sourceLocation, value);
		return printStatement;
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		SequenceStatement sequenceStatement = new SequenceStatement(sourceLocation, statements);
		return sequenceStatement;
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		ReadVariableExpression readVariableExpression = new ReadVariableExpression(sourceLocation, variableName);
		return readVariableExpression;
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		ReadParameterExpression readParameterExpression = new ReadParameterExpression(sourceLocation, parameterName);
		return readParameterExpression;
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		FunctionCallExpression functionCallExpression = new FunctionCallExpression(sourceLocation, functionName, actualArgs);
		return functionCallExpression;
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		ChangeSignExpression changeSignExpression = new ChangeSignExpression(sourceLocation, expression);
		return changeSignExpression;
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		NotExpression notExpression = new NotExpression(sourceLocation, expression);
		return notExpression;
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		DoubleLiteralExpression doubleLiteralExpression = new DoubleLiteralExpression(location, value);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		NullExpression nullExpression = new NullExpression(location);
		return nullExpression;
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

}
