@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    authService.register(request);
    return ResponseEntity.ok("success");
}