client.test("Request executed successfully", ()=> {
    client.assert(response.status === 200, "Response status is not 200");

    const type = response.contentType.mimeType;
    client.assert(type === "application/json", "Expected 'application/json' but received '" + type + "'");
});
