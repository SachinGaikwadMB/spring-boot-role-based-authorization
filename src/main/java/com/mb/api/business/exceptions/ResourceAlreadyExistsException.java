package com.mb.api.business.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;

	public ResourceAlreadyExistsException(String message)
	{
		super(message);
	}

	public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue)
	{
		super(String.format("%s Already exists with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName()
	{
		return resourceName;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public Object getFieldValue()
	{
		return fieldValue;
	}
}
