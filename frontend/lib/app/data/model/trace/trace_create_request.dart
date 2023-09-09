class TraceCreateRequest {
  final int boothId;
  final String memo;

  TraceCreateRequest({
    required this.boothId,
    required this.memo,
  });

  Map<String, dynamic> toJson() => {
    'boothId': boothId,
    'memo': memo,
  };
}