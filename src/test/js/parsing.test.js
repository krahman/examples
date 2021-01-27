const amf = require('amf-client-js');

beforeAll(() => {
    // initializes all AMF plugins, although we only need WebApi Plugin and AMF Core functionality
    amf.AMF.init();

});

test('parse OAS 2.0', () => {
    // Get the parser type corresponding to the source API type
    const parser = new amf.Oas20Parser();

    // A BaseUnit is the return type of any parsing
    // The actual object can be many different things, depending on the content of the source file
    // https://github.com/aml-org/amf/blob/develop/documentation/model.md#baseunit
    parser.parseFileAsync('file://resources/examples/banking-api.raml').then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();

    }).catch(err => console.log(err));
})

test('parse OAS 2.0 from string', () => {
    const parser = new amf.Oas20Parser();

    const api =
        '{\n' +
        '  \'swagger\': \'2.0\',\n' +
        '  \'info\': {\n' +
        '    \'title\': \'ACME Banking HTTP API\',\n' +
        '    \'version\': \'1.0\'\n' +
        '  },\n' +
        '  \'host\': \'acme-banking.com\'' +
        '}';

    parser.parseStringAsync(api).then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();
        expect(model.raw).toEqual(api);

    }).catch(err => console.log(err));
})

test('parse OAS 3.0', () => {
    const parser = new amf.Oas30Parser();

    parser.parseFileAsync('file://resources/examples/banking-api-oas30.json').then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();

    }).catch(err => console.log(err));
})


test('parse RAML 1.0', () => {
    const parser = new amf.Raml10Parser();

    parser.parseFileAsync('file://resources/examples/banking-api.raml').then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();

    }).catch(err => console.log(err));
})

test('parse RAML 1.0 from string', () => {
    const parser = new amf.Raml10Parser();

    const api =
        "#%RAML 1.0\n" +
        "\n" +
        "title: ACME Banking HTTP API\n" +
        "version: 1.0";

    parser.parseStringAsync(api).then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();
        expect(model.raw).toEqual(api);

    }).catch(err => console.log(err));
})

test('parse RAML 0.8', () => {
    const parser = new amf.Raml08Parser();

    parser.parseFileAsync('file://resources/examples/banking-api-08.raml').then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();

    }).catch(err => console.log(err));
})

test('parse RAML 0.8 from string', () => {
    const parser = new amf.Raml08Parser();

    const api =
        "#%RAML 0.8\n" +
        "\n" +
        "title: ACME Banking HTTP API\n" +
        "version: 1.0";

    parser.parseStringAsync(api).then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();
        expect(model.raw).toEqual(api);

    }).catch(err => console.log(err));
})

test('parse AMF Graph', () => {
    const parser = new amf.AmfGraphParser();

    parser.parseFileAsync('file://resources/examples/banking-api.jsonld').then(model => {
        expect(model).not.toBeNull();
        expect(model).toBeDefined();

    }).catch(err => console.log(err));
})
