package com.example.gradprojectnov.model;

public enum RatingEnum {
	U,
	UA{
		public String toString() {
			return "U/A 13+";
		}
	},
	A,
	S,
}
