package se.jola.model;

public abstract class AbstractEntity {

	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public AbstractEntity setId(Long id) {
		this.id = id;
		return this;
	}
	
}
