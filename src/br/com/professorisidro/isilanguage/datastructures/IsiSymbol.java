package br.com.professorisidro.isilanguage.datastructures;

public abstract class IsiSymbol {

	protected String name;
	protected boolean isUsed;

	public abstract String generateJavaCode();

	public abstract String generateDartCode();

	public IsiSymbol(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setUsed() {
        this.isUsed = true;
    }

	@Override
	public String toString() {
		return "IsiSymbol [isUsed=" + isUsed + ", name=" + name + "]";
	}

}
