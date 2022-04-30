client.test("Request executed successfully", ()=> {
    client.assert(response.status === 200, "Response status is not 200");
});
