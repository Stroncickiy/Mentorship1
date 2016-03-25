package com.epam.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ClassExplorer {
	private Class<?> clazz;

	public void assignClassByName(String nameOfClass) {
		try {
			setClazz(Class.forName(nameOfClass));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to construct element");
		}
	}

	public void printAllFields() {
		Field[] declaredFields = getTargetClass().getDeclaredFields();
		if (declaredFields.length > 0) {
			for (Field field : declaredFields) {
				System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " "
						+ field.getName());
			}
		} else {
			System.out.println("There are no fields in class " + getTargetClass().getName());
		}
	}

	public void printAllConstructors() {
		Constructor<?>[] constructors = getTargetClass().getDeclaredConstructors();
		if (constructors.length > 0) {
			StringBuilder stringBuilder = new StringBuilder();
			for (Constructor<?> constructor : constructors) {
				stringBuilder.append(Modifier.toString(constructor.getModifiers()));
				stringBuilder.append(" ");
				stringBuilder.append(getTargetClass().getSimpleName());
				stringBuilder.append(methodParametersToString(constructor.getParameters()));
				stringBuilder.append(exceptionTypesToString(constructor.getExceptionTypes()));
				stringBuilder.append(" {\n}\n");
			}
			System.out.println(stringBuilder.toString());
		} else {
			System.out.println("There are no constructors in class " + getTargetClass().getName());
		}

	}

	public void printAllMethods() {
		Method[] declaredMethods = getTargetClass().getDeclaredMethods();
		if (declaredMethods.length > 0) {
			StringBuilder stringBuilder = new StringBuilder();
			for (Method method : declaredMethods) {
				stringBuilder.append("\n");
				stringBuilder.append(Modifier.toString(method.getModifiers()));
				stringBuilder.append(" ");
				stringBuilder.append(method.getReturnType().getSimpleName());
				stringBuilder.append(" ");
				stringBuilder.append(method.getName());
				stringBuilder.append(" ");
				stringBuilder.append(methodParametersToString(method.getParameters()));
				stringBuilder.append(exceptionTypesToString(method.getExceptionTypes()));
				stringBuilder.append(" {\n}\n");
			}
			System.out.println(stringBuilder.toString());
		} else {
			System.out.println("There are no methods in class " + getTargetClass().getName());
		}

	}

	private String exceptionTypesToString(Class<?>[] exceptions) {
		StringBuilder builder = new StringBuilder();
		if (exceptions.length > 0) {
			builder.append(" throws ");
		}
		for (Class<?> exception : exceptions) {
			builder.append(exception.getSimpleName() + ",");
		}
		if (exceptions.length > 0) {
			builder.setLength(builder.length() - 1);
		}

		return builder.toString();
	}

	private String methodParametersToString(Parameter[] parameters) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("( ");
		for (Parameter parameter : parameters) {
			stringBuilder.append(Modifier.toString(parameter.getModifiers()));
			stringBuilder.append(" ");
			stringBuilder.append(parameter.getType().getSimpleName());
			stringBuilder.append(" ");
			stringBuilder.append(parameter.getName());
			stringBuilder.append(",");
		}
		stringBuilder.setLength(stringBuilder.length() - 1);
		stringBuilder.append(" )");
		return stringBuilder.toString();
	}

	public Class<?> getTargetClass() {
		return clazz;
	}

	private void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
