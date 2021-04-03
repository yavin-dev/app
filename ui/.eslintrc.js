'use strict';

module.exports = {
  root: true,
  parser: '@typescript-eslint/parser',
  plugins: ['ember', 'prettier', 'qunit', '@typescript-eslint'],
  extends: [
    'eslint:recommended',
    'plugin:ember/recommended',
    'plugin:qunit/recommended',
    'plugin:@typescript-eslint/recommended',
    'prettier',
    'prettier/@typescript-eslint',
  ],
  env: {
    browser: true,
  },
  rules: {
    'ember/no-jquery': 'warn',

    'ember/no-computed-properties-in-native-classes': 'warn',

    // Some of these don't seem possible to fix yet
    'ember/use-ember-data-rfc-395-imports': 'off',

    // cleanliness & consistency
    'prefer-const': 'off', // const has misleading safety implications
    'prefer-rest-params': 'off', // useful for super(...arguments) calls

    // typescript
    '@typescript-eslint/no-unused-vars': ['error', { argsIgnorePattern: '^_' }],
    '@typescript-eslint/explicit-function-return-type': 'off',
    '@typescript-eslint/ban-ts-ignore': 'off',

    // prettier
    'prettier/prettier': 'error',

    // better handled by prettier:
    '@typescript-eslint/indent': 'off',

    // globally disable, but enable for ts files below
    '@typescript-eslint/explicit-module-boundary-types': 'off',
  },
  overrides: [
    // node files
    {
      files: [
        '.eslintrc.js',
        '.prettierrc.js',
        '.template-lintrc.js',
        'ember-cli-build.js',
        'index.js',
        'testem.js',
        'blueprints/*/index.js',
        'config/**/*.js',
        'server/**/*.js',
      ],
      parserOptions: {
        sourceType: 'script',
      },
      env: {
        browser: false,
        node: true,
      },
      plugins: ['node'],
      extends: ['plugin:node/recommended'],
      rules: {
        'node/no-unpublished-require': 'off', // https://github.com/mysticatea/eslint-plugin-node/issues/77
        '@typescript-eslint/camelcase': 'off',
        '@typescript-eslint/no-var-requires': 'off',
      },
    },
    {
      // enable the rule specifically for TypeScript files
      files: ['*.ts'],
      rules: {
        '@typescript-eslint/explicit-module-boundary-types': ['error'],
      },
    },
    {
      files: ['app/mirage/routes/**'],
      rules: {
        'ember/no-get': 'off',
      },
    },
  ],
};
