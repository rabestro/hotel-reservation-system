client.test("Request returns status 404 (not found)", () => {
    client.assert(response.status === 404, "Response status is not 404 (Not found)");
});
