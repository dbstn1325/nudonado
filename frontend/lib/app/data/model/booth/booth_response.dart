class BoothResponse {
  final int id;

  BoothResponse({required this.id});

  factory BoothResponse.fromJson(Map<String, dynamic> json) {
    return BoothResponse(id: json['id']);
  }
}
