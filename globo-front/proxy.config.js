const proxy = [
{
    context: '/v1',
    target: 'http://localhost:8080',
    pathRewrite: { '^/v1': ''}
}
];

module.exports = proxy;