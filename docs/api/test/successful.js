client.test("Request executed successfully", () => {
    client.assert(response.status === 200, "Expected response status 200 but received '" + response.status + "'");

    const type = response.contentType.mimeType;
    client.assert(type === "application/hal+json", "Expected 'application/hal+json' but received '" + type + "'");
});
