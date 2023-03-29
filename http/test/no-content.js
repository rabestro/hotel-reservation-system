client.test("Request executed successfully", () => {
    client.assert(response.status === 204, "Response status is not 204 (No Content)");
});
