# Front

## Setup

You can install the project dependencies by running:

```shell
npm ci
```

Then setup svelte-kit and the git hooks with:

```shell
npm run prepare
```

## Tests

### Unit

You can run the project's unit tests with:

```shell
npm run test:unit run
```

### E2E

You can install the dependencies for playwright with:

```shell
npx playwright install --with-deps
```

You can then run the project's e2e tests with:

```shell
npm run test:e2e
```

## Lint

You can lint the project with prettier and eslint with:

```shell
npm run lint
```
