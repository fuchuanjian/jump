
package loon.core;

public abstract class Asset {

	public String AssetName;

	public Asset(String name) {
		this.AssetName = name;
	}

	public abstract void load();

}
