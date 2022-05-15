const code = 401;
client.test(`Request executed with response status ${code}`, () => {
    client.assert(response.status === code,
        `Expected response status is ${code} but actual is ${response.status}`);
});
