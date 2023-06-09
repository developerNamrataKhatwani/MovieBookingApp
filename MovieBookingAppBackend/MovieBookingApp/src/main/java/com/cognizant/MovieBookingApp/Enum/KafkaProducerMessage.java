package com.cognizant.MovieBookingApp.Enum;

public enum KafkaProducerMessage {
	BOOK_MOVIE("Movie booked successfully", "Error in booking movie.", "API is not authorized to book the movie."),

	ADD_MOVIE("New Movie added successfully.", "Failed to add new movie.",
			"API is not authorized for adding the movie."),

	DELETE_MOVIE("Movie deleted successfully.", "Failed to delete the movie",
			"API is not authorized for delete the movie."),

	FIND_MOVIE_BY_NAME("Movie found successfully", "Movie not found.",
			"API is not authorized for search movie by name."),

	UPDATE_MOVIE("Movie update successfully", "Movie not updated.", "API is not authorized to update the movie."),

	FIND_ALL_MOVIE("All movie fetched successfully", "Movie list is empty.",
			"API is not authorized to find all movies. "),

	DELETE_TRANSACTION("Transaction deleted successfully", "Transaction was not deleted",
			"API is not authorized to delete the transaction.");

	private String success;
	private String failed;
	private String authorizeStatus;

	private KafkaProducerMessage(String success, String failed, String authorizeStatus) {
		this.success = success;
		this.failed = failed;
		this.authorizeStatus = authorizeStatus;
	}

	public String getSuccess() {
		return success;
	}

	public String getFailed() {
		return failed;
	}

	public String getAuthorizeStatus() {
		return authorizeStatus;
	}

}
