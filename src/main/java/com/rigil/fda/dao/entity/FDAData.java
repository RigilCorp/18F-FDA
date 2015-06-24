package com.rigil.fda.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "fda_data")
public class FDAData {
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "FDA_DATA_ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long id;
		
	@Column(name="FDA_DATA_NAME", length =45, nullable=false)
	private String dataName;
	
	@Column(name="FDA_DATA_CD", length=20, unique=true, nullable=false)
	private String dataCode;
	
	@Column(name="FDA_DATA_DESC", length=45, nullable=false)
	private String dataDescription;
	
	//SETTERS and GETTERS
	
	public Long getId() {
		return id;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getDataDescription() {
		return dataDescription;
	}

	public void setDataDescription(String dataDescription) {
		this.dataDescription = dataDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((dataName == null) ? 0 : dataName.hashCode());
		result = prime * result
				+ ((dataCode == null) ? 0 : dataCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((dataDescription == null) ? 0 : dataDescription.hashCode());
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
		FDAData other = (FDAData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (dataName == null) {
			if (other.dataName != null)
				return false;
		} else if (!dataName.equals(other.dataName))
			return false;
		if (dataCode == null) {
			if (other.dataCode != null)
				return false;
		} else if (!dataCode.equals(other.dataCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (dataDescription == null) {
			if (other.dataDescription != null)
				return false;
		} else if (!dataDescription.equals(other.dataDescription))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", dataName=" + dataName + ", dataCode="
				+ dataCode + ", dataDescription="
				+ dataDescription 
				+ "]";
	} 

}
