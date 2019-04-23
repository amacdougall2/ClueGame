package clueGame;

public class Solution {
	
	public String person;
	public String room;
	public String weapon;
	
	public Solution(String person, String room, String weapon) {
		super();
		this.person = person;
		this.room = room;
		this.weapon = weapon;
	}
	public boolean equals(Solution other) {
		System.out.println(this);
		System.out.println(other);
		return(this.person.equals(other.person) && this.room.equals(other.room) && this.weapon.equals(other.weapon));
	}
	@Override
	public String toString() {
		return "Solution [person=" + person + ", room=" + room + ", weapon=" + weapon + "]";
	}
}
