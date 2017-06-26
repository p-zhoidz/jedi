package by.pzh.jedi.domain;

import java.io.Serializable;
import java.util.List;

public class Bundle implements Serializable {
	private boolean active;
	private List<ScholarlyItem> scholarlyItems;
	private List<PublicationArtifact> publicationArtifacts;
	
	public boolean isActive() {
		return active;
	}
	public Bundle setActive(boolean active) {
		this.active = active;
		return this;
	}
	public List<ScholarlyItem> getScholarlyItems() {
		return scholarlyItems;
	}
	public Bundle setScholarlyItems(List<ScholarlyItem> scholarlyItems) {
		this.scholarlyItems = scholarlyItems;
		return this;
	}
	public List<PublicationArtifact> getPublicationArtifacts() {
		return publicationArtifacts;
	}
	public Bundle setPublicationArtifacts(List<PublicationArtifact> publicationArtifacts) {
		this.publicationArtifacts = publicationArtifacts;
		return this;
	}
	
	
	
	
}
