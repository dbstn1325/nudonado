class Trace {
  final int id;
  final int boothId;
  final String category;
  final String memo;

  Trace({required this.id, required this.boothId, required this.category, required this.memo});

  factory Trace.fromJson(Map<String, dynamic> json) {
    return Trace(
      id: json['id'],
      boothId: json['boothId'],
      category: json['category'],
      memo: json['memo'],
    );
  }
}
