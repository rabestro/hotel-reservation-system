client.test("Request executed successfully", () => {
    client.assert(response.status === 403, "Response status is not 403 (Forbidden)");
});
