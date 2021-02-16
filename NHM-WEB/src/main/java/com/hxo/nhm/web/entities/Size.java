package com.hxo.nhm.web.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

@Entity
public class Size implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotNull(message = "Size is missing")
	private String name;
	
	@OneToMany(mappedBy = "size", orphanRemoval = true, 
	cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Color> colors;

	@ManyToOne
	private Item item;
	
	public Size () {
	}
	
	public Size (Item item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colors == null) ? 0 : colors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Size other = (Size) obj;
		if (colors == null) {
			if (other.colors != null)
				return false;
		} else if (!colors.equals(other.colors))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}