package com.orchestranetworks.ps.cache;

public abstract class CachedObject
{
	private final long expiration;
	private final Boolean external;

	public CachedObject(long expiration, Boolean external)
	{
		this.expiration = expiration == 0 ? 0 : expiration + System.currentTimeMillis();
		this.external = external;
	}

	public boolean isExpired()
	{
		if (expiration == 0 || expiration > System.currentTimeMillis())
		{
			return false;
		}
		return true;
	}

	public long getExpiration()
	{
		return expiration;
	}

	public Boolean isExternal()
	{
		return external;
	}

}